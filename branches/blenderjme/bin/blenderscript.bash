#!/bin/sh -p
# This script is Bash-shell specific.
# Using the interpreter line above to accommodate WinGW, which has no /bin/bash.

PROGNAME="${0##*/}"

# $Id$
#
# You can feed command-line args to your script by supplying comand-line
# args to this program after "--".  Examples
#    path/to/blenderscript.bash   # Runs the script that you type in
#    path/to/blenderscript.bash script.py s2.py # Runs the specified scripts
#    path/to/blenderscript.bash script.py -- alpha beta
# Regarding this last example, your script will get a sys.argv list like:
#     ['./blender', '-P', '/tmp/blenderscript-24266.py', '--', 'one', 'two']
#
# Export env variable VERBOSE to cause this script to echo the important stuff.
#  VERBOSE mode also retains the temp file.
# Export env variable NORUN  to skip the actual blender invocation at the end.
# Export env variable BLENDER_SWITCHES to pass through extra command-line
#  parameters to the Blender invocation.
#
# By default will run non-graphically (i.e., "headless"), and Blender will
#  exit after executing your Python code.
# If you want to run with defaults (probably full-screen), export FULLSCREEN=1.
# To set your own startup coords, export COORDS to:
#  x y-from-bottom width height, like:  export COORDS='200 200 800 600'
#  Blender does not like some height values.  Height of 600 seems to work well.
# Both graphical modes will leave Blender running after the script runs (you
#  can add a Blender.Quit() to the end of your script to force it to exit).
#
# Copyright (c) 2009, Blaine Simpson and the jMonkeyEngine team
# All rights reserved.
#
# Redistribution and use in source and binary forms, with or without
# modification, are permitted provided that the following conditions are met:
#     * Redistributions of source code must retain the above copyright
#       notice, this list of conditions and the following disclaimer.
#     * Redistributions in binary form must reproduce the above copyright
#       notice, this list of conditions and the following disclaimer in the
#       documentation and/or other materials provided with the distribution.
#     * Neither the name of the <organization> nor the
#       names of its contributors may be used to endorse or promote products
#       derived from this software without specific prior written permission.
#
# THIS SOFTWARE IS PROVIDED BY Blaine Simpson and the jMonkeyEngine team
# ''AS IS'' AND ANY
# EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
# WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
# DISCLAIMED. IN NO EVENT SHALL Blaine Simpson or the jMonkeyEngine team
# BE LIABLE FOR ANY
# DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
# (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
# LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
# ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
# (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
# SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

shopt -s xpg_echo
set +u

Failout() {
    echo "Aborting $PROGNAME:  $*" 1>&2
    exit 1
}
[ -n "$TMPDIR" ] || TMPDIR=/tmp
TMPFILE="$TMPDIR/${PROGNAME%%.*}-$$.py"

declare -a scripts
while [ $# -gt 0 ]; do
    case "$1" in
       '--') break 2;;
        *) scripts=("${scripts[@]}" "$1");;
    esac
    shift
done
#echo ${#scripts[@]} scripts

type -t blender >&- || Failout 'Blender is not in your env search path'

# Purposefully not writing an interpreter line
# The following test is perfectly logical and efficient.  Unfortunately, some
# bug with MinGW is causing this to generate failure status even though the
# write is completely successful.  Therefore, the verbose test follows.
#echo -n "# Blender Python script generated by $PROGNAME at " > "$TMPFILE" ||
[ -f "$TMPFILE" ] && Failout "Temp file '$TMPFILE' already exists????"
echo  "# Blender Python script generated by $PROGNAME at \c" >> "$TMPFILE"
[ -f "$TMPFILE" ] || Failout "Failed to write temp file '$TMPFILE'"

[ -n "$VERBOSE" ] || trap "rm '$TMPFILE'" EXIT
# Retain the temp file for debugging or re-use purposes if env var set

date >> "$TMPFILE"
# N.b.: Critically important below that the imports are AFTER the user-supplied
# code, since this latter may contain Blender.Load() which wipes out imports.
echo 'try:\n    pass' >> "$TMPFILE"
case ${#scripts[@]} in
    0) echo 'Enter script text:' 1>&2; while read; do echo "    $REPLY"; done ;;
    *) for script in "${scripts[@]}"; do
           echo "    execfile('$script')"
       done >> "$TMPFILE";;
esac >> "$TMPFILE"
echo 'except Exception, e:
    from sys import exc_info
    from traceback import tb_lineno
    ei = exc_info()[2]
    while ei:
        print "  " + ei.tb_frame.f_code.co_filename + ":" + str(tb_lineno(ei))
        ei = ei.tb_next
    print e' >> "$TMPFILE"
[ -n "$VERBOSE" ] ||
echo '    print "Rerun with VERBOSE env. var. set to retain the temporary file."' >> "$TMPFILE"
echo '\nfrom os.path import isfile
if isfile("BlenderQuickStart.pdf"):
    print "We recommend that you do not run Blender from the Blender installation directory"' >> "$TMPFILE"

# Set up Blender screen parameters.
declare -a ScreenParams
if [ -n "$COORDS" ]; then
    ScreenParams=(-p ${COORDS})
elif [ -n "$FULLSCREEN" ]; then
    unset ScreenParams
else
    ScreenParams=(-b /dev/null -noglsl)
fi

case "$0" in */*) SCRIPTRELDIR="${0%/*}";; *) SCRIPTRELDIR=".";; esac

[ -z "$PYTHONPATH" ] && [ -d "${SCRIPTRELDIR}/../src" ] && {
	export PYTHONPATH="${SCRIPTRELDIR}/../src"
	[ -n "$VERBOSE" ] && echo "PYTHONPATH set to '$PYTHONPATH'"
}
#chmod +x "$TMPFILE"   Blender doesn't need to be executable, so safer without

[ -z "$FULLSCREEN" ] && [ -z "$COORDS" ] &&
echo '* IGNORE the warning message below saying: Loading X failed:... *' 1>&2
[ -n "$VERBOSE" ] &&
echo \
blender "${ScreenParams[@]}" -noaudio -noglsl -P "$TMPFILE" $BLENDER_SWITCHES "$@" 1>&2
[ -n "$NORUN" ] && exit 0

# Would prefer to exec blender, but that prevents the EXIT trap from working
blender "${ScreenParams[@]}" -noaudio -noglsl -P "$TMPFILE" $BLENDER_SWITCHES "$@"
