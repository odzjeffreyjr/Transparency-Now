package com.odzjeffrey.workoutpartner

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private lateinit var rv: RecyclerView
private lateinit var list: ArrayList<Post>

class CommunityFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_community, container, false)
        list = initPostList()
        rv = view.findViewById<RecyclerView>(R.id.rvCommunity)!!
        rv.adapter = context?.let { PostAdapter(it, list) }
        rv.layoutManager = LinearLayoutManager(context)
        return view
    }

    private fun initPostList(): ArrayList<Post>{
        val a = Post(R.drawable.postpic1, R.drawable.profilepic1, "SEBC", "Crucial deadlines for Wakiso Local Government payment missed", "Earlier this week, the local government of Wakiso was probed on a disbursement that came beyond the deadline.")
        val b = Post(R.drawable.postpic2, R.drawable.profilepic2, "The Weekly", "Alarm as leak reveals plans by officials to divert millions", "On Saturday 17th 2029, Transparency Now uncovered a grand embezzlement plan that initially raised internal suspicions.")
        val c = Post(R.drawable.postpic3, R.drawable.profilepic1, "SEBC", "Here's how you can find out what your leaders are up to!", "Transparency Now celebrates 3 months of successful operation in Morocco. This tutorial on how to utilise fully our platform...")
        val d = Post(R.drawable.postpic4, R.drawable.profilepic3, "Huffle Posteur", "Mushanana confirms suspension of 10 officers at Ministry of Transport", "Following the March 2029 report by Transparency Now that uncovered masked to ploys to divert ministry funds, Minister of...")
        val e = Post(R.drawable.postpic5, R.drawable.profilepic4, "Daily Monitor", "Moving from Awareness to Accountability: Charlotte Mannya", "The monthly accountability awareness campaign organised in collaboration with Transparency Now has just come to a close. Charlotte...")
        val f = Post(R.drawable.postpic6, R.drawable.profilepic5, "New Vision", "Loopholes in Budget revealed. Parliament votes.", "President Cyril Ramaphosa on Wednesday cast his vote on the budgetary debate following Transparency's report on misuse of resources...")
        val g = Post(R.drawable.postpic7, R.drawable.profilepic6, "Transparency Now", "Police spending under the lens: Report", "Transparency is glad to unveil its monthly report on accountability within the police. The report highlights aspects such as...")
        val h = Post(R.drawable.postpic8, R.drawable.profilepic1, "SEBC", "Transparency Now: The bold Vision for accountability in Africa", "Transparency Now is now operating in over 40 African countries. Our Bold mission for Africa identifies lack of accountability as...")
        val i = Post(R.drawable.postpic9, R.drawable.profilepic6, "Transparency Now", "Transparency around the week: October Report", "Download the Weekly Accountability Report on all the data that has been collected by Transparency Now or continue in Web Browser.")
        val j = Post(R.drawable.postpic10, R.drawable.profilepic3, "Huffle Posteur", "Transparency around the week: November Report", "Download the Weekly Accountability Report on all the data that has been collected by Transparency Now or continue in Web Browser.")
        var returnList = ArrayList<Post>()
        returnList.add(a)
        returnList.add(b)
        returnList.add(c)
        returnList.add(d)
        returnList.add(e)
        returnList.add(f)
        returnList.add(g)
        returnList.add(h)
        returnList.add(i)
        returnList.add(j)
        return returnList
    }
}