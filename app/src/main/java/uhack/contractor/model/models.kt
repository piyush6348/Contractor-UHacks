package uhack.contractor.model

/**
 * Created by nimit on 15/10/17.
 */


data class Contractor(val Name:String, val address:String,
                      val email:String, val mobile:Long, val contractorID:String,
                      val skillType:Int, val workerIDs: ArrayList<String>)


data class ContractorTemp(val contractorId:String="", val amount:Int=0, val duration:Int=0,
                          val time:String="",val Name:String="", val type:Int=0)


data class Transaction(val transactionID: String, val inventoryID: String,
                       val comment: String, val type:Int, val creatorID:String, val delta:Int)

data class Worker(val contractorID: String, val workerID:String, val name: String,
                   val mobile: String)