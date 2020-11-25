package com.finai

/** *************************************
 * author:      zhangguoqing-phq
 * createDate:  2020/11/23 19:06
 * description: StructuredStreaming network wordcount
 * ************************************** */
import org.apache.spark.sql.functions._
import org.apache.spark.sql.SparkSession

object MySparkStructuredNetwordCount {
  def main(args: Array[String]): Unit = {
    //System.setProperty("hadoop.home.dir", "F:\\stuliper\\data\\hadoop\\hadoop2.6\\hadoop-2.6.0")
    val spark = SparkSession
      .builder
      .appName("StructuredNetworkWordCount")
      .master("local[3]")
      .getOrCreate()

    import spark.implicits._

    // Create DataFrame representing the stream of input lines from connection to localhost:9999
    val lines = spark.readStream
      .format("socket")
      .option("host", "10.8.199.73")
      .option("port", 9999)
      .load()

    // Split the lines into words
    val words = lines.as[String].flatMap(_.split(" "))

    // Generate running word count
    val wordCounts = words.groupBy("value").count()

    // Start running the query that prints the running counts to the console
    val query = wordCounts.writeStream
      .outputMode("complete")
      .format("console")
      .start()

    query.awaitTermination()
  }
}
