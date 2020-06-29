package me.buck.cloudmusic

object Config {

    const val REST_URL = "http://192.168.0.103:3000"

    // <name , img>
    val funcButtons = listOf(
        Pair(R.string.func_button_1, R.drawable.t_dragonball_icn_daily),
        Pair(R.string.func_button_2, R.drawable.t_dragonball_icn_playlist),
        Pair(R.string.func_button_3, R.drawable.t_dragonball_icn_rank),
        Pair(R.string.func_button_4, R.drawable.t_dragonball_icn_radio),
        Pair(R.string.func_button_5, R.drawable.t_dragonball_icn_look),
        Pair(R.string.func_button_6, R.drawable.t_dragonball_icn_birthday),
        Pair(R.string.func_button_7, R.drawable.t_dragonball_icn_birthday),
        Pair(R.string.func_button_8, R.drawable.t_dragonball_icn_birthday)
    )

    var statusBarHeight = 0
}