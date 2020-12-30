import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.URLEncoder


object UrlUtils {
    fun getParamsString(params: HashMap<String, String>): String {
        val result = StringBuilder()
        for ((key, value) in params) {
            result.append(URLEncoder.encode(key, "UTF-8"))
            result.append("=")
            result.append(URLEncoder.encode(value, "UTF-8"))
            result.append("&")
        }
        val resultString = result.toString()
        return if (resultString.isNotEmpty()) resultString.substring(0, resultString.length - 1) else resultString
    }

    fun getResponse(inputStream : InputStream) : String{
        BufferedReader(
            InputStreamReader(inputStream)
        ).let {
            var inputLine: String?
            val content = StringBuffer()
            while (it.readLine().also { inputLine = it } != null) {
                content.append(inputLine)
            }
            it.close()

            return content.toString()
        }
    }

}