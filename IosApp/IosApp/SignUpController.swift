//
//  SignUo.swift
//  IosApp
//
//  Created by Mahmut Karali on 8.06.2019.
//  Copyright © 2019 Mahmut Karali. All rights reserved.
//

import UIKit

class SignUpController : UIViewController {
    @IBOutlet weak var username: UITextField!
    @IBOutlet weak var passwd: UITextField!
    @IBOutlet weak var againpassw: UITextField!
    
    @IBAction func signUpClick(_ sender: Any) {
        let json: [String: Any] = ["name": username.text!,
                                   "password": passwd.text!]
        let jsonData = try? JSONSerialization.data(withJSONObject: json)
        // create post request
        let url = URL(string: "https://webapicoremongodb20190504094558.azurewebsites.net/api/User")!
        var request = URLRequest(url: url)
        request.setValue("application/json", forHTTPHeaderField: "Content-Type")
        request.httpMethod = "POST"
        
        // insert json data to the request
        request.httpBody = jsonData
        
        let task = URLSession.shared.dataTask(with: request) { data, response, error in
            guard let data = data, error == nil else {
                print(error?.localizedDescription ?? "No data")
                return
            }
            let responseJSON = try? JSONSerialization.jsonObject(with: data, options: [])
            if let responseJSON = responseJSON as? [String: Any] {
                print(responseJSON)
                let alert = UIAlertController(title: "Kullanıcı Kayıt", message: "Kayıt işlemi başarılı !", preferredStyle: .alert)
                
                alert.addAction(UIAlertAction(title: "Yes", style: .default, handler: nil))
                
                self.present(alert, animated: true)
            }
        }
        task.resume()
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
    }
}
