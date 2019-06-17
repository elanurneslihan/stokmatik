using MongoDB.Bson;
using MongoDB.Bson.Serialization.Attributes;

namespace WebApplication1.Models
{
    public class Product
    {

        [BsonId]
        [BsonRepresentation(BsonType.ObjectId)]
        public string Id { get; set; }

        [BsonElement("productName")]
        public string ProductName { get; set; }

        [BsonElement("productCode")]
        public string ProductCode { get; set; }

        [BsonElement("barcode")]
        public string Barcode { get; set; }

        [BsonElement("piece")]
        public int Piece { get; set; }

    }
}
