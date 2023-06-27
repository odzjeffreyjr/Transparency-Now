package com.odzjeffrey.transparencynow

class Forum {
    var topic: String? = null
    var newMessages: String? = null
    var preview: String? = null

    constructor(topic: String?, newMessages: String?, preview: String?){
        this.topic = topic
        this.newMessages = newMessages
        this.preview = preview
    }
}
