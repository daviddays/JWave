/**
 * JWave is distributed under the MIT License (MIT); this file is part of.
 *
 * Copyright (c) 2008-2015 Christian Scheiblich (cscheiblich@gmail.com)
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package math.jwave.datatypes.blocks;

import java.util.HashMap;

import math.jwave.datatypes.lines.Line;
import math.jwave.datatypes.lines.LineHash;
import math.jwave.exceptions.JWaveException;
import math.jwave.exceptions.JWaveFailure;

/**
 * Uses HashMap generic for sparse data representations.
 * 
 * @author Christian Scheiblich (cscheiblich@gmail.com)
 * @date 16.05.2015 16:41:53
 */
public class BlockHash extends Block {

  /**
   * Storing LineHash objects in a HashMap for sparse representation.
   * 
   * @author Christian Scheiblich (cscheiblich@gmail.com)
   * @date 16.05.2015 16:43:03
   */
  HashMap< Integer, Line > _hashMapLines;

  /**
   * Create an object of a sub type; e.g. as pattern.
   * 
   * @author Christian Scheiblich (cscheiblich@gmail.com)
   * @date 24.05.2015 13:58:16
   */
  public BlockHash( ) {
    super( );
  } // BlockHash

  /**
   * Copy constructor that takes over - if available - the values of another
   * type of block.
   * 
   * @author Christian Scheiblich (cscheiblich@gmail.com)
   * @date 24.05.2015 13:59:49
   * @param block
   *          object of type block; e.g. BlockFull
   */
  public BlockHash( Block block ) {
    super( block );

    // TODO copy values form Block object

  } // BlockHash

  /**
   * @author Christian Scheiblich (cscheiblich@gmail.com)
   * @date 16.05.2015 16:41:53
   * @param noOfRows
   * @param noOfCols
   */
  public BlockHash( int noOfRows, int noOfCols ) {
    super( noOfRows, noOfCols );

    _hashMapLines = new HashMap< Integer, Line >( );

  } // BlockHash

  /**
   * Passing information that takes the block as a part of a global structure;
   * e.g. a SuperBlock.
   * 
   * @author Christian Scheiblich (cscheiblich@gmail.com)
   * @date 24.05.2015 14:03:36
   * @param offSetRow
   *          the global off set of rows of the block
   * @param offSetCol
   *          the global off set of columns of the block
   * @param noOfRows
   *          the number of rows of the block
   * @param noOfCols
   *          the number of columns of the block
   */
  public BlockHash( int offSetRow, int offSetCol, int noOfRows, int noOfCols ) {

    super( offSetRow, offSetCol, noOfRows, noOfCols );

    _hashMapLines = new HashMap< Integer, Line >( );

  } // BlockHash

  /*
   * Getter!
   * @author Christian Scheiblich (cscheiblich@gmail.com)
   * @date 16.05.2015 16:41:53 (non-Javadoc)
   * @see math.jwave.datatypes.blocks.Block#get(int, int)
   */
  @Override public double get( int i, int j ) throws JWaveException {

    check( i, j );

    Line line = null;
    double value = 0.;

    if( _hashMapLines.containsKey( j ) ) {

      line = _hashMapLines.get( j );

      value = line.get( i );

    } else
      throw new JWaveFailure( "Line - no value stored for requested i: " + i );

    return value;

  } // get

  /*
   * TODO Comment me please!
   * @author Christian Scheiblich (cscheiblich@gmail.com)
   * @date 16.05.2015 16:41:53 (non-Javadoc)
   * @see math.jwave.datatypes.blocks.Block#set(int, int, double)
   */
  @Override public void set( int i, int j, double value ) throws JWaveException {

    check( i, j );

    Line line = null;

    if( _hashMapLines.containsKey( j ) ) {

      line = _hashMapLines.get( j );
      line.set( i, value );

    } else {

      line = new LineHash( _noOfRows );
      line.set( i, value );
      _hashMapLines.put( j, line );

    } // if

  } // set

} // class
