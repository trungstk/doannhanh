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
    public class CartsController : ApiController
    {
        private Model1 db = new Model1();

        // GET: api/Carts
        public IQueryable<Cart> GetCarts()
        {
            return db.Carts;
        }
        [HttpGet]
        [Route("api/Cart/GetCartAccpunt/TotalMoney/{id_account}")]
        public IHttpActionResult GetTotalMoney(int id_account)
        {
            var cart = db.Carts.Where(x => x.Id_Account == id_account).Sum(x => x.TotalMoney);

            if (cart==null)
            {
                return NotFound();
            }
            return Ok(cart);
        }
        [HttpGet]
        [Route("api/Cart/GetCartAccpunt/{id_account}")]
        public IHttpActionResult GetCartAccpunt(int id_account)
        {
            var cart = db.Carts.Where(x => x.Id_Account == id_account);
            if (!cart.Any())
            {
                return NotFound();
            }
            return Ok(cart);
        }

        [HttpPut]
        [Route("api/Cart/PutCart/{id}")]
        public IHttpActionResult PutCart(int id, Cart cart)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != cart.Id_Cart)
            {
                return BadRequest();
            }

            db.Entry(cart).State = EntityState.Modified;

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!CartExists(id))
                {
                    return NotFound();
                }
                else
                {
                    throw;
                }
            }

            return Ok(cart);
        }

        [HttpPost]
        [Route("api/Cart/PostCart")]
        public IHttpActionResult PostCart(Cart cart)
        {
            var Carts_ = db.Carts.Where(x => x.Id_Account == cart.Id_Account && x.Id_productdetails == cart.Id_productdetails );  
            if (!Carts_.Any())
            {
                db.Carts.Add(cart);
                db.SaveChanges();
                return NotFound();
            }

            return Ok(Carts_);
        }

        [HttpDelete]
        [Route("api/Cart/DeleteCart/{id}")]
        public IHttpActionResult DeleteCart(int id)
        {
            Cart cart = db.Carts.Find(id);
            if (cart == null)
            {
                return NotFound();
            }

            db.Carts.Remove(cart);
            db.SaveChanges();

            return Ok(cart);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool CartExists(int id)
        {
            return db.Carts.Count(e => e.Id_Cart == id) > 0;
        }
    }
}