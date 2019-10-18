import com.github.kittinunf.fuel.core.Headers
import com.github.kittinunf.fuel.httpGet

fun main() {
    doHttpCall()
}

fun doHttpCall() {

    val url = "https://www2.yggtorrent.pe/engine/search?name=lion+king&description=&file=&uploader=&category=2143&sub_category=all&do=search"
    val urlGeneric = "http://www2.yggtorrent.pe/top/day"

    val (request, response, result) = urlGeneric
        .httpGet()
        .header(Headers.USER_AGENT, "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.169 Safari/537.36")
        .responseString()

    println(request)
    println(response)


}
