

import UIKit

class AddProductController: UIViewController {
    
    @IBOutlet weak var stock: UITextField!
    @IBOutlet weak var code: UITextField!
    @IBOutlet weak var barcode: UITextField!
    @IBOutlet weak var name: UITextField!
    
    @IBAction func click(_ sender: Any) {
        let json: [String: Any] = ["name": name.text!,
                                   "code": code.text!,
                                   "barcode": barcode.text!,
                                   "stock": stock.text!]
        
        let jsonData = try? JSONSerialization.data(withJSONObject: json)
        
        // create post request
        let url = URL(string: "https://webapicoremongodb20190504094558.azurewebsites.net/api/Product")!
        var request = URLRequest(url: url)
        request.setValue("application/json", forHTTPHeaderField: "Content-Type")
        request.httpMethod = "POST"
        
        request.httpBody = jsonData
        
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
    
    override func viewDidLoad() {
        super.viewDidLoad()
    }
}
