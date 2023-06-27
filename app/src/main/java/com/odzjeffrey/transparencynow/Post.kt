package com.odzjeffrey.workoutpartner

import android.net.Uri
import android.widget.Button

class Post {
    var postUsername: String? = null
    var postProfilePicture: Int
    var postTitle: String? = null
    var postImageView: Int
    var blab: String

    constructor(postImageView: Int, postProfilePicture: Int,  postUsername: String, postTitle: String, blab: String){
        this.postImageView = postImageView
        this.postUsername = postUsername
        this.postProfilePicture = postProfilePicture
        this.postTitle = postTitle
        this.blab = blab
    }
}
