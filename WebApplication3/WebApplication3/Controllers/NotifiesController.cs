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
    public class NotifiesController : ApiController
    {
        private Model1 db = new Model1();

        [HttpGet]
        [Route("api/Notifies/GetNotifys/{Id_Account}")]
        public IHttpActionResult GetNotify(int Id_Account)
        {
            var notify = db.Notifies.Where(x => x.Id_Account == Id_Account && x.Status != 0);
            if (!notify.Any())
            {
                return NotFound();
            }

            return Ok(notify);
        }
        [HttpGet]
        [Route("api/Notifies/GetNotifies/{Id_Account}")]
        public IHttpActionResult GetNotifies(int Id_Account)
        {
            var notify = db.Notifies.Where(x => x.Id_Account == Id_Account && x.watched == 0);
            if (!notify.Any())
            {
                return NotFound();
            }

            return Ok(notify);
        }

        [HttpPut]
        [Route("api/Notifies/PutNotify/{id}")]
        public IHttpActionResult PutNotify(int id, Notify notify)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != notify.Id_notify)
            {
                return BadRequest();
            }

            db.Entry(notify).State = EntityState.Modified;

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!NotifyExists(id))
                {
                    return NotFound();
                }
                else
                {
                    throw;
                }
            }

            return Ok(notify);
        }

        // POST: api/Notifies
        [HttpPost]
        [Route("api/Notifies/PostNotify")]
        public IHttpActionResult PostNotify(Notify notify)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.Notifies.Add(notify);
            db.SaveChanges();

            return Ok(notify);
        }

        // DELETE: api/Notifies/5
        [ResponseType(typeof(Notify))]
        public IHttpActionResult DeleteNotify(int id)
        {
            Notify notify = db.Notifies.Find(id);
            if (notify == null)
            {
                return NotFound();
            }

            db.Notifies.Remove(notify);
            db.SaveChanges();

            return Ok(notify);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool NotifyExists(int id)
        {
            return db.Notifies.Count(e => e.Id_notify == id) > 0;
        }
    }
}