using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Entity;
using System.Data.Entity.Infrastructure;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using System.Web.Http.Description;
using WebApplication3.Models;

namespace WebApplication3.Controllers
{
    public class ProductsController : ApiController
    {
        private Model1 db = new Model1();


        [HttpGet]
        [Route("api/Product/GetProductDiscount")]
        public IHttpActionResult GetProductDiscount()
        {
            var Product = db.Products.OrderBy(q => Guid.NewGuid()).Take(10);

            List<Product> products = new List<Product>();
            if (!Product.Any())
            {
                return NotFound();
            }
            foreach (Product product in Product)
            {
                var productDetails = db.ProductDetails.Where(x => x.Id_product == product.Id_product);
                List<ProductDetail> productDetaillist = new List<ProductDetail>();
                foreach (ProductDetail productDetail in productDetails)
                {
                    productDetaillist.Add(productDetail);
                }

                if (productDetaillist[0].Promotionalprice != 0)
                {
                    products.Add(product);
                }

            }
            return Ok(products);
        }
        [HttpGet]
        [Route("api/Product/GetProductPopular")]
        public IHttpActionResult GetProductPopular()
        {
            var products = db.Products.OrderByDescending(x => x.sales).Take(10);
            return Ok(products);
        }

        [HttpGet]
        [Route("api/Products/GetProducts")]
        public IQueryable<Product> GetProducts()
        {
            return db.Products.OrderBy(q => Guid.NewGuid()).Take(20);
        }


        [HttpGet]
        [Route("api/Products/GetProductsPage/{pageNumber}/{pageSize}")]
        public IEnumerable<Product> GetCustomer(int pageNumber, int pageSize)
        {
            var source = db.Products.OrderBy(x => x.Id_product).AsQueryable().Skip((pageNumber - 1) * pageSize).Take(pageSize).ToList();
            return source;

        }
        [HttpGet]
        [Route("api/Products/GetSearchProduct/{search}")]
        public IHttpActionResult GetSearchProduct(string search)
        {
            var san_pham = db.Products.Where(x => x.NameProduct.Contains(search.ToLower()));
            if (!san_pham.Any())
            {
                return NotFound();

            }
            return Ok(san_pham);
        }

        [HttpGet]
        [Route("api/Products/GetProductCategory/{pageNumber}/{pageSize}/{id_category}")]
        public IEnumerable<Product> GetProductCategory(int pageNumber, int pageSize, int id_category)
        {
            var source = db.Products.Where(x => x.Id_danhmuc == id_category)
                .OrderBy(x => x.Id_product).AsQueryable().Skip((pageNumber - 1) * pageSize).
                Take(pageSize).ToList();
            return source;

        }
        [HttpGet]
        [Route("api/Products/GetSearchProductCategory/{search}/{id_category}")]
        public IHttpActionResult GetSearchProductCategory(string search,int id_category)
        {
            var san_pham = db.Products.Where(x =>x.Id_danhmuc==id_category && x.NameProduct.Contains(search.ToLower()));
            if (!san_pham.Any())
            {
                return NotFound();

            }
            return Ok(san_pham);
        }
        [HttpGet]
        [Route("api/Products/GetProduct/{id}")]
        public IHttpActionResult GetProduct(int id)
        {
            Product product = db.Products.Find(id);
            if (product == null)
            {
                return NotFound();
            }

            return Ok(product);
        }
        [HttpGet]
        [Route("api/Products/GetProductLike")]
        public IHttpActionResult GetProductLike()
        {
            var sanpham = db.Products.OrderBy(q => Guid.NewGuid()).Take(10);
            sanpham.Reverse();
            return Ok(sanpham);

        }
        // PUT: api/Products/5
        [ResponseType(typeof(void))]
        public IHttpActionResult PutProduct(int id, Product product)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != product.Id_product)
            {
                return BadRequest();
            }

            db.Entry(product).State = EntityState.Modified;

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!ProductExists(id))
                {
                    return NotFound();
                }
                else
                {
                    throw;
                }
            }

            return StatusCode(HttpStatusCode.NoContent);
        }

        // POST: api/Products
        [ResponseType(typeof(Product))]
        public IHttpActionResult PostProduct(Product product)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.Products.Add(product);
            db.SaveChanges();

            return CreatedAtRoute("DefaultApi", new { id = product.Id_product }, product);
        }

        // DELETE: api/Products/5
        [ResponseType(typeof(Product))]
        public IHttpActionResult DeleteProduct(int id)
        {
            Product product = db.Products.Find(id);
            if (product == null)
            {
                return NotFound();
            }

            db.Products.Remove(product);
            db.SaveChanges();

            return Ok(product);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool ProductExists(int id)
        {
            return db.Products.Count(e => e.Id_product == id) > 0;
        }
    }
}