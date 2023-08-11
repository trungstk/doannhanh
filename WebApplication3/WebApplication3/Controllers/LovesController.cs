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
    public class LovesController : ApiController
    {
        private Model1 db = new Model1();

        // GET: api/Loves
        public IQueryable<Love> GetLoves()
        {
            return db.Loves;
        }
        [HttpGet]
        [Route("api/Love/checklove/{Id_Account}")]
        public IHttpActionResult checkLove(int Id_Account)
        {
            var yeuthich = db.Loves.Where(x => x.Id_Account == Id_Account);

            if (!yeuthich.Any())
            {
                return NotFound();
            }

            return Ok(yeuthich);
        }
        [HttpGet]
        [Route("api/Love/checklove/{Id_Account}/{Id_product}")]
        public IHttpActionResult check(int Id_Account, int Id_product)
        {
            var yeuthich = db.Loves.Where(x => x.Id_Account == Id_Account && x.Id_product == Id_product);

            if (!yeuthich.Any())
            {
                return NotFound();
            }

            return Ok(yeuthich);
        }

        // PUT: api/Loves/5
        [ResponseType(typeof(void))]
        public IHttpActionResult PutLove(int id, Love love)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != love.Id_love)
            {
                return BadRequest();
            }

            db.Entry(love).State = EntityState.Modified;

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!LoveExists(id))
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

        [HttpPost]
        [Route("api/Love/PostLove")]
        public IHttpActionResult PostLove(Love love)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.Loves.Add(love);
            db.SaveChanges();

            return CreatedAtRoute("DefaultApi", new { id = love.Id_love }, love);
        }

        [HttpPost]
        [Route("api/Love/DeleteLove/{id}")]
        public IHttpActionResult DeleteLove(int id)
        {
            Love love = db.Loves.Find(id);
            if (love == null)
            {
                return Ok(false);
            }

            db.Loves.Remove(love);
            db.SaveChanges();

            return Ok(true);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool LoveExists(int id)
        {
            return db.Loves.Count(e => e.Id_love == id) > 0;
        }
    }
}