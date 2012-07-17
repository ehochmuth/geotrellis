package geotrellis.op.stat

import geotrellis._
import geotrellis.op._
import geotrellis.process._
import geotrellis.stat._

/**
 * Build a histogram (using the [[geotrellis.stat.MapHistogram]] strategy) from
 * this raster.
 */
case class MapHistogram(r:Op[Raster]) extends Op1(r) ({
    (raster) => {
      val histogram = geotrellis.stat.FastMapHistogram()
      val data = raster.data.asArray.getOrElse(sys.error("need array"))

      var i = 0
      val len = raster.length
      while (i < len) {
        if (data(i) != NODATA) histogram.countItem(data(i), 1)
        i += 1
      }
      Result(histogram.asInstanceOf[geotrellis.stat.Histogram])
    }
})