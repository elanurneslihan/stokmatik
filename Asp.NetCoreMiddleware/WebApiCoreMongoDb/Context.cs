using MongoDB.Driver;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using WebApiCoreMongoDb.Models;
using WebApplication1.Models;

namespace WebApiCoreMongoDb
{
    public class Context
    {
        private readonly IMongoDatabase database;

        public Context()
        {
            database = new MongoClient("mongodb://134.209.224.148:27017").GetDatabase("testlab");
        }

        public IMongoCollection<User> Users
        {
            get
            {
                return database.GetCollection<User>("User");
            }
        }

        public IMongoCollection<Product> Products
        {
            get
            {
                return database.GetCollection<Product>("Product");
            }
        }
    }
}
