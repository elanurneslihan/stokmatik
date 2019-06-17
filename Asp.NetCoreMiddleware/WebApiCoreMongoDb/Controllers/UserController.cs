using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using WebApiCoreMongoDb.Models;
using MongoDB.Driver;
using WebApplication1.Models;

namespace WebApiCoreMongoDb.Controllers
{
    [Produces("application/json")]
    [Route("api/User")]
    public class UserController : Controller
    {
        private Context context;

        public UserController()
        {
            context = new Context();
        }

        // GET: api/Post
        [HttpGet]
        //public IEnumerable<User> Get()
        public UserList Get()
        {
            UserList list = new UserList();
            //var posts = context.Users.Find(_ => true).ToList();
            list.list = context.Users.Find(_ => true).ToList();
            return list;
        }

        // GET: api/Post/5
        [HttpGet("{title}", Name = "Get")]
        public IEnumerable<User> Get(string title)
        {
            //var posts = context.Posts.Find(x => x.Title == title).ToList();
            //return posts;
            var posts = context.Users.Find(_ => true).ToList();
            return posts;
        }

        // POST: api/Post
        [HttpPost]
        public void Post([FromBody]User user)
        {
            context.Users.InsertOne(user);
        }

        // PUT: api/Post/5
        [HttpPut("{id}")]
        public void Put(string id, [FromBody]Post post)
        {
            //var filter = Builders<Post>.Filter.Eq(s => s.Id, id);
            //var update = Builders<Post>.Update
            //                .Set(s => s.Title, post.Title)
            //                .Set(s => s.Description, post.Description);

            //context.Posts.UpdateOne(filter, update);
        }

        // DELETE: api/ApiWithActions/5
        [HttpDelete("{id}")]
        public void Delete(string id)
        {
            context.Users.DeleteOneAsync(Builders<User>.Filter.Eq("Id", id));
        }
    }
}
