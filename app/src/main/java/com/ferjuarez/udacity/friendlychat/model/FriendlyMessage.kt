package com.ferjuarez.udacity.friendlychat.model

import java.util.*

data class FriendlyMessage (var text: String = "", var name: String = "", var photoUrl: String? = "", var date: Date = Date())
