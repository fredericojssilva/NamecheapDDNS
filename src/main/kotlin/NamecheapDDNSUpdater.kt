import java.net.HttpURLConnection
import java.net.URL

class NamecheapDDNSUpdater {

    fun updateIp(ip: String, ncConfigs: NCConfigs?) {
        ncConfigs?.entries?.forEach {
            val parameters = hashMapOf(
                "host" to it.host,
                "domain" to it.domain,
                "password" to ncConfigs.password,
                "ip" to ip
            )

            val url = "https://dynamicdns.park-your-domain.com/update?${UrlUtils.getParamsString(parameters)}"
            sendRequest(URL(url))
        }
    }

    private fun sendRequest(url: URL) {
        val con: HttpURLConnection = url.openConnection() as HttpURLConnection
        con.requestMethod = "GET"
        con.responseCode.let {
            if (it == 200) {
                println("SUCCESS")
            } else {
                System.err.println("Error sendRequest() - $it")
            }
        }

    }
}