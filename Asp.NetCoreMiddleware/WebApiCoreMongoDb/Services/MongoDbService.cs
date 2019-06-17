using MongoDB.Bson;
using MongoDB.Driver;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using WebApplication1.Models;

namespace WebApplication1.Services
{
    public class MongoDbService
    {
        private IMongoCollection<User> UserCollection { get; }

        public MongoDbService(string dbUri, string dbName, string collectionName)
        {
            var mongoClient = new MongoClient(dbUri);
            var mongoDb = mongoClient.GetDatabase(dbName);

            UserCollection = mongoDb.GetCollection<User>(collectionName);
        }

        public async Task<List<User>> GetAllUsers()
        {
            var users = new List<User>();
            var allDoc = await UserCollection.FindAsync(new BsonDocument());
            await allDoc.ForEachAsync(doc => users.Add(doc));

            return users;
        }
    }
}
