package com.capstone.passionventure

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class JobListView : AppCompatActivity(), JobListAdapter.OnItemClickListener {

    private lateinit var jobListAdapter: JobListAdapter
    private lateinit var jobList: List<JobItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job_list_view)

        // Generate sample job list
        jobList = generateJobList()
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        // Set up RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        jobListAdapter = JobListAdapter(jobList, this)
        recyclerView.adapter = jobListAdapter

        val searchTab = findViewById<EditText>(R.id.searchTab)

        // Set up EditText listener for filtering
        searchTab.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                filterJobs(s.toString())
            }
        })
    }

    private fun filterJobs(query: String) {
        val filteredList = mutableListOf<JobItem>()
        for (item in jobList) {
            if (item.jobCategory.contains(query, true)) {
                filteredList.add(item)
            }
        }
        jobListAdapter.filterList(filteredList)
    }


    override fun onItemClick(position: Int) {

        val dummyDetails = getString(R.string.dummy_details)
        val clickedItem = jobList[position]
        val intent = Intent(this, JobDetailsView::class.java)
        intent.putExtra("jobDescription", clickedItem.jobDesc)
        intent.putExtra("jobCompany", clickedItem.jobCompany)
        intent.putExtra("jobCategory", clickedItem.jobCategory)
        intent.putExtra("jobDetails", dummyDetails )
        startActivity(intent)
    }


    private fun generateJobList(): List<JobItem> {
        val jobList = mutableListOf<JobItem>()

        // Sample job data with categories
        jobList.add(JobItem("50K Sign-on Bonus | Apply Now! CSR-Voice | Start Monday | HMO + Fixed weekends off!", "✅iPloy, OPC Inc.", "Customer Service"))
        jobList.add(JobItem("Senior Software Engineer | Full-time | Remote work available | Competitive salary", "🚀Tech Innovations Ltd.", "Technology"))
        jobList.add(JobItem("Marketing Manager | Part-time | Flexible hours | Bonus opportunities", "🎯Digital Marketing Solutions", "Marketing"))
        jobList.add(JobItem("Customer Support Specialist | Contract | 24/7 shifts | Health benefits", "💼Supportive Solutions Inc.", "Customer Service"))
        jobList.add(JobItem("Data Analyst | Internship | Paid | 3 months | Immediate start", "📊Data Insights Co.", "Data Analysis"))

        return jobList
    }
}

