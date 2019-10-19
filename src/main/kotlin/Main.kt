import okhttp3.Headers
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response


val client = OkHttpClient().newBuilder()
    .followRedirects(false)
    .build()

val urlGeneric = "https://www2.yggtorrent.pe/engine/ajax_top_query/day"


fun main() {

    var currentUrl: String = ""
    lateinit var currentHeaders: Headers
    var cookiesString: String = ""
    lateinit var currentResponse: Response

    currentResponse = makeRequest(urlGeneric, "")
    // TODO: Récupérer les cookies
    currentResponse.headers.forEach { (key, value) ->
        //println("$key : $value")
        if (key == "set-cookie") {
            cookiesString += "; $value"
        }
    }
    currentUrl = currentResponse.headers["location"]!!
    currentHeaders = currentResponse.headers

    // TODO: Deuxième call
    currentResponse = makeRequest(currentResponse.headers["location"]!!.replace("http", "https"), cookiesString)

    println(currentResponse.body)

    /*while (currentUrl.isNotEmpty()) {
        currentResponse = makeRequest(currentUrl, currentHeaders)
        currentUrl = currentResponse.headers["Location"] ?: ""
        currentHeaders = currentResponse.headers

        if (currentResponse.code == 301) {
            println(currentResponse.body?.string())
        }

    } */

    // println(currentResponse.body)

}

fun makeRequest(url: String, cookie: String): Response {

    val request = Request.Builder().url(url)
        .header(
            "accept",
            "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3"
        )
        .header("accept-encoding", "gzip, deflate, br")
        .header("accept-language", "en-GB,en-US;q=0.9,en;q=0.8")
        .header("sec-fetch-site", "none")
        .header("upgrade-insecure-requests", "1")
        .header(
            "user-agent",
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.120 Safari/537.36"
        )
        .header("cookie", cookie)
        .build()

    return client.newCall(request).execute()
}

fun doHttpCall() {

    val headers: Map<String, String> = mapOf(
        "authority" to "www2.yggtorrent.pe",
        "upgrade-insecure-requests" to "1",
        "user-agent" to "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.120 Safari/537.36",
        "accept" to "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3",
        "sec-fetch-site" to "none",
        "accept-encoding" to "gzip, deflate",
        "accept-language" to "en-GB,en-US;q=0.9,en;q=0.8"
    )

    val url =
        "https://www2.yggtorrent.pe/engine/search?name=lion+king&description=&file=&uploader=&category=2143&sub_category=all&do=search"

    val google = "https://www.google.fr"


}
