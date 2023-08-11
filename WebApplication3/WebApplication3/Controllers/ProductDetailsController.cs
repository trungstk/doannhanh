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
    public class ProductDetailsController : ApiController
    {
        private Model1 db = new Model1();

        // GET: api/ProductDetails
        public IQueryable<ProductDetail> GetProductDetails()
        {
            return db.ProductDetails;
        }
   
        // GET: api/ProductDetails/5
        [HttpGet]
        [Route("api/ProductDetails/GetProductDetail/{id}")]
        public IHttpActionResult GetProductDetail(int id)
        {
            ProductDetail productDetail = db.ProductDetails.Find(id);
            if (productDetail == null)
            {
                return NotFound();
            }

            return Ok(productDetail);
        }
        // GET: api/ProductDetails/5
        [HttpGet]
        [Route("api/ProductDetails/GetProductDetails/{Id_product}")]
        public IHttpActionResult GetProductDetails(int Id_product)
        {
            var productDetail = db.ProductDetails.Where(x => x.Id_product == Id_product);
            if (!productDetail.Any())
            {
                return NotFound();
            }

            return Ok(productDetail);
        }
        // PUT: api/ProductDetails/5
        [ResponseType(typeof(void))]
        public IHttpActionResult PutProductDetail(int id, ProductDetail productDetail)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != productDetail.Id_productdetails)
            {
                return BadRequest();
            }

            db.Entry(productDetail).State = EntityState.Modified;

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!ProductDetailExists(id))
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

        // POST: api/ProductDetails
        [ResponseType(typeof(ProductDetail))]
        public IHttpActionResult PostProductDetail(ProductDetail productDetail)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.ProductDetails.Add(productDetail);
            db.SaveChanges();

            return CreatedAtRoute("DefaultApi", new { id = productDetail.Id_productdetails }, productDetail);
        }

        // DELETE: api/ProductDetails/5
        [ResponseType(typeof(ProductDetail))]
        public IHttpActionResult DeleteProductDetail(int id)
        {
            ProductDetail productDetail = db.ProductDetails.Find(id);
            if (productDetail == null)
            {
                return NotFound();
            }

            db.ProductDetails.Remove(productDetail);
            db.SaveChanges();

            return Ok(productDetail);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool ProductDetailExists(int id)
        {
            return db.ProductDetails.Count(e => e.Id_productdetails == id) > 0;
        }
    }
}