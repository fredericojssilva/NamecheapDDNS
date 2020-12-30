import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.io.File
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

const val CONFIG_FILE_PATH = "nc_configs.json"
var IP: String? = null
val ncConfigs = getConfigs()
val runExec = ::run

fun main() {
    ncConfigs?.run {
        val executorService = Executors.newSingleThreadScheduledExecutor()
        executorService.scheduleAtFixedRate(runExec, 0, updateTimeInMinutes.toLong(), TimeUnit.MINUTES)
    } ?: println("ERROR: $CONFIG_FILE_PATH does not exist or it is invalid")
}

private fun getConfigs(): NCConfigs? {
    File(CONFIG_FILE_PATH).let {
        if (it.exists()) {
            it.readText()
        } else {
            null
        }
    }?.also {
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build().run {
                val jsonAdapter: JsonAdapter<NCConfigs> = adapter(NCConfigs::class.java)
                return jsonAdapter.fromJson(it)
            }
    }

    return null
}

private fun run() {
    try {
        MyIP().getMyIP()?.let {
            //only send NC update if IP changes
            if (IP != it) {
                IP = it
                println(it)
                NamecheapDDNSUpdater().updateIp(
                    ip = it,
                    ncConfigs = ncConfigs
                )
            }
        }
    } catch (exception: Exception) {
        System.err.println("ERROR: Not able to get IP\n${exception.localizedMessage}")
    }

}



