import java.lang.Exception
import java.net.*


class MyIP {

    @Throws(Exception::class)
    fun getMyIP(): String? {
        val url = URL("http://ifconfig.me/ip")
        val con: HttpURLConnection = url.openConnection() as HttpURLConnection
        con.requestMethod = "GET"
        if (con.responseCode == 200) {
            UrlUtils.getResponse(con.inputStream)?.let {
                return if (checkIPv4(it)) {
                    it
                } else {
                    System.err.println("ERROR: not a valid ip")
                    null
                }
            }

        } else {
            System.err.println("ERROR: something went wrong getting IP")
        }
        return null
    }

    private fun checkIPv4(ip: String?): Boolean {
        val isIPv4: Boolean
        isIPv4 = try {
            val inet = InetAddress.getByName(ip)
            inet.hostAddress == ip && inet is Inet4Address
        } catch (e: UnknownHostException) {
            false
        }
        return isIPv4
    }
}