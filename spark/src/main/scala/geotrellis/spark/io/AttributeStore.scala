package geotrellis.spark.io

import geotrellis.spark._

trait AttributeStore {
  type ReadableWritable[T]

  def read[T: ReadableWritable](layerId: LayerId, attributeName: String): T
  def write[T: ReadableWritable](layerId: LayerId, attributeName: String, value: T): Unit
}