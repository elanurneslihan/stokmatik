using MongoDB.Bson;
using MongoDB.Bson.Serialization.Attributes;
using System.Collections.Generic;

namespace WebApplication1.Models
{
    public class UserList
    {
        public List<User> list{ get; set; }

    }
}