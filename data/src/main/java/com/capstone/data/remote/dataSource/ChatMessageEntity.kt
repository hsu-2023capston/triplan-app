package com.capstone.data.remote.dataSource

data class ChatMessageEntity(
    var tid: Int?,
    var uid: Int?,
    var name: String?,
    var img: Int?,
    var content: String?,
    var timestamp: String?
){
    constructor() : this(-1,-1,"",-1,"","")
}
