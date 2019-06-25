 

import UIKit

class ListController : UITableViewController {

    var array:[String] = [""]
    var refresher: UIRefreshControl!
    
    override func viewDidLoad()
    {
        super.viewDidLoad()
        
        refresher = UIRefreshControl()
        refresher.attributedTitle = NSAttributedString(string: "Pull to refresh")
        refresher.addTarget(self, action: #selector(ListController.populate), for: UIControl.Event.valueChanged)
        tableView.addSubview(refresher)
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    
    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        // #warning Incomplete implementation, return the number of rows
        return array.count
    }
    
    
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "cell", for: indexPath)
        cell.textLabel?.text = String(array[indexPath.row])
        return cell
    }
    
    @objc func populate()
    {
         let url = URL(string: "https://webapicoremongodb20190504094558.azurewebsites.net/api/Product")
         
         URLSession.shared.dataTask(with: url! as URL) { data, response, error in
         var flag = true
         if let content = data {
         do{
         let myJson = try JSONSerialization.jsonObject(with: content , options: JSONSerialization.ReadingOptions.mutableContainers) as! [String:Any]
         
             for (key,value) in myJson
             {
                 if (key == "list"){
                     print (value)
                     let jsonDictionary = value as? [[String : Any]] ;
                    
                     for dict in jsonDictionary!{
                        var temp = "Adı : " + ( dict["name"]! as! String ) + " Barkod : " + (dict["barcode"]! as! String);
                        self.array.append(temp)
                     }
                 }
             }
         } catch{
         }
         }
         }.resume()

        tableView.reloadData()
        refresher.endRefreshing()
    }
}
