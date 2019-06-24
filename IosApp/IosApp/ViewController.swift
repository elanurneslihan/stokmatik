
import UIKit
import Foundation

struct User: Decodable {
    let list: [Item]
}

struct Item: Decodable {
    let id: String
    let name: String
    let password: String
}

class ViewController: UIViewController {
    @IBOutlet weak var username: UITextField!
    @IBOutlet weak var password: UITextField!
    @IBOutlet weak var label: UILabel!
    @IBOutlet weak var label2: UILabel!
 
    @IBOutlet weak var gotomainpage: UIButton!
    
    @IBAction func click(_ sender: Any) {
        let url = URL(string: "https://webapicoremongodb20190504094558.azurewebsites.net/api/User")

       URLSession.shared.dataTask(with: url! as URL) { data, response, error in
       
        var flag = true
        if let content = data {
            do{
                let myJson = try JSONSerialization.jsonObject(with: content , options: JSONSerialization.ReadingOptions.mutableContainers) as! [String:Any]
 
                for (key,value) in myJson
                {
                    if (key == "list"){
                        print (value)
                        if let array : [[String : String ]] = value as? [[String :String]] {
                            for dict in array{
                                //if(dict["name"]! == "neslihan" && dict["password"]! == "rr33!.")
                                if(dict["name"]! == self.username.text! && dict["password"]! == self.password.text!){
                                    flag = false
                                }
                            }
                        }
                    }
                }
                
                if(flag){
                    let alertController = UIAlertController(title: "iOScreator", message: "Hello, world!", preferredStyle: .alert)
                    alertController.addAction(UIAlertAction(title: "Dismiss", style: .default))
                    self.present(alertController, animated: true, completion: nil)
                }else{
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
