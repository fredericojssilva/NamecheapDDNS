data class NCConfigs(
    val password: String,
    val updateTimeInMinutes: Int,
    val entries: List<Entry>
) {

    data class Entry(
        val domain: String,
        val host: String
    )
}