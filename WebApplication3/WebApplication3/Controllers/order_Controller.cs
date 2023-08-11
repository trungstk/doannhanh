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
    public class order_Controller : ApiController
    {
        private Model1 db = new Model1();

        // GET: api/order_
        public IQueryable<order_> Getorder_()
        {
            return db.order_;
        }
        [HttpGet]
        [Route("api/order/Getorder/{id_Account}/{Status}")]
        public IHttpActionResult Getorder(int id_Account, int Status)
        {
            var order_ = db.order_.Where(x=>x.Id_Account== id_Account && x.Status==Status);
            if (!order_.Any())
            {
                return NotFound();
            }

            return Ok(order_);
        }
        [HttpGet]
        [Route("api/order/GetorderAccount/{id_Account}")]
        public IHttpActionResult GetorderAccount(int id_Account)
        {
            var order_ = db.order_.Where(x=>x.Id_Account== id_Account );
            if (!order_.Any())
            {
                return NotFound();
            }

            return Ok(order_);
        }


        [HttpPut]
        [Route("api/order/Putorder_/{id}")]
        public IHttpActionResult Putorder_(int id, order_ order_)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != order_.Id_Order)
            {
                return BadRequest();
            }

            db.Entry(order_).State = EntityState.Modified;

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!order_Exists(id))
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

        // POST: api/order_
        [HttpPost]
        [Route("api/order/Postorder")]
        public IHttpActionResult Postorder_(order_ order_)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.order_.Add(order_);
            db.SaveChanges();

            return Ok(order_);
        }

        [HttpDelete]
        [Route("api/order/Deleteorder/{id}")]
        public IHttpActionResult Deleteorder_(int id)
        {
            order_ order_ = db.order_.Find(id);
            if (order_ == null)
            {
                return NotFound();
            }

            db.order_.Remove(order_);
            db.SaveChanges();

            return Ok(order_);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool order_Exists(int id)
        {
            return db.order_.Count(e => e.Id_Order == id) > 0;
        }
    }
}