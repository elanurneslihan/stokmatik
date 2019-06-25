 

import UIKit

class DeleteProductController: UIViewController {
    @IBOutlet weak var barcode: UITextField!

    @IBAction func click(_ sender: Any) {
        let brc = self.barcode.text! ;
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
                                let bbb = dict["barcode"]! as? String
                                if(bbb == brc){
                                    print(dict["id"]!)
                                    
                                    let url = URL(string: "https://webapicoremongodb20190504094558.azurewebsites.net/api/Product/" + (dict["id"]!  as! String))!
                                    var request = URLRequest(url: url)
                                    request.setValue("application/json", forHTTPHeaderField: "Content-Type")
                                    request.httpMethod = "DELETE"
                                    
                                    let task = URLSession.shared.dataTask(with: request) { data, response, error in
                                        guard let data = data, error == nil else {
                                            print(error?.localizedDescription ?? "No data")
                                            return
                                        }
                                        let responseJSON = try? JSONSerialization.jsonObject(with: data, options: [])
                                        if let responseJSON = responseJSON as? [String: Any] {
                                            print(responseJSON)
                                        }
                                    }
                                    task.resume()
                                }
                            }
                        }
                    }
                }catch{
                }
            }
            }.resume()
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
    }
}
