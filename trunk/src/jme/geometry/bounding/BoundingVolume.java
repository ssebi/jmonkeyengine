/*
 * Copyright (c) 2003, jMonkeyEngine - Mojo Monkey Coding
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without 
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this 
 * list of conditions and the following disclaimer. 
 * 
 * Redistributions in binary form must reproduce the above copyright notice, 
 * this list of conditions and the following disclaimer in the documentation 
 * and/or other materials provided with the distribution. 
 * 
 * Neither the name of the Mojo Monkey Coding, jME, jMonkey Engine, nor the 
 * names of its contributors may be used to endorse or promote products derived 
 * from this software without specific prior written permission. 
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" 
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE 
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE 
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE 
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR 
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF 
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS 
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN 
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) 
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE 
 * POSSIBILITY OF SUCH DAMAGE.
 *
 */
package jme.geometry.bounding;

import jme.entity.camera.Frustum;
import jme.math.Vector;

/**
 * <code>BoundingVolume</code> defines an interface for dealing with containment
 * of a collection of points. 
 * @author Mark Powell
 * @version $Id: BoundingVolume.java,v 1.3 2003-09-10 20:32:59 mojomonkey Exp $
 */
public interface BoundingVolume {
    /**
     * <code>hasCollision</code> will determine if this volume is colliding
     * (touching in any way) with another volume.
     * @param sourceOffset defines the position of the entity containing
     *      this volume, if null it is ignored.
     * @param volume the bounding volume to compare.
     * @param targetOffset defines the position of the entity containing
     *      the target volume, if null it is ignored.
     * @return true if there is a collision, false otherwise.
     */
    public boolean hasCollision(Vector sourceOffset, BoundingVolume volume, 
            Vector targetOffset);
    
    /**
     * <code>distance</code> calculates the distance between this volume and
     * a given volume. If there is a problem, a distance of -1 is returned.
     * @param sourceOffset defines the position of the entity containing
     *      this volume, if null it is ignored.
     * @param volume the bounding volume to compare.
     * @param targetOffset defines the position of the entity containing
     *      the target volume, if null it is ignored.
     * @return the distance between this volume and a given volume.
     */
    public float distance(Vector sourceOffset, BoundingVolume volume, 
            Vector targetOffset);
    
    /**
     * <code>isVisible</code> determines if the bounding volume is visible
     * within a viewing frustum. A offset is used to modify the center of
     * the bounding volume with the position of the entity to be checked.
     * @param offsetPosition the position of the entity that may contain the
     *      bounding volume. If null is passed, offset is ignored.
     * @param frustum the view frustum.
     * @return true if the object is visible, false otherwise.
     */
    public boolean isVisible(Vector offsetPosition, Frustum frustum);
    
    /**
     * <code>setCollisionBuffer</code> sets the value that must be reached to
     * consider bounding volumes colliding. By default this value is 0.
     * @param buffer the collision buffer.
     */
    public void setCollisionBuffer(float buffer);
}
