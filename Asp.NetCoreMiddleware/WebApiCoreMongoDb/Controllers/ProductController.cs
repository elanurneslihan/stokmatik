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
    [Route("api/Product")]
    public class ProductController : Controller
    {
        private Context context;

        public ProductController()
        {
            context = new Context();
        }

        // GET: api/Post
        [HttpGet]
        public IEnumerable<Product> Get()
        {
            //var posts = context.Posts.Find(_ => true).ToList();
            var posts = context.Products.Find(_ => true).ToList();
            return posts;
        }

        [HttpGet("{product}")]
        // GET: api/Post/5
        public IEnumerable<Product> Get(string product)
        {
            //var posts = context.Posts.Find(x => x.Title == title).ToList();
            //return posts;
            var posts = context.Products.Find(q => q.ProductName == product).ToList();
            return posts;
        }

        // POST: api/Post
        [HttpPost]
        public void Post([FromBody]Product product)
        {
            context.Products.InsertOne(product);
        }

        // PUT: api/Post/5
        [HttpPut("{id}")]
        public void Put(string id, [FromBody]Product product)
        {
             //var filter = Builders<Post>.Filter.Eq(s => s.Id, id);
             //var update = Builders<Post>.Update
                           //  .Set(s => s.Title, post.Title)
                           //  .Set(s => s.Description, post.Description);

            //context.Posts.UpdateOne(filter, update);
        }

        // DELETE: api/ApiWithActions/5
        [HttpDelete("{productid}")]
        public void Delete(string productid)
        {
            context.Products.DeleteOneAsync(Builders<Product>.Filter.Eq("Id", productid));
        }
    }
}