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
    public class AccountsController : ApiController
    {
        private Model1 db = new Model1();

        // GET: api/Accounts/5
        [HttpGet]
        [Route("api/account/getaccount{id}")]
        public IHttpActionResult GetAccount(int id)
        {
            Account account = db.Accounts.Find(id);
            if (account == null)
            {
                return NotFound();
            }

            return Ok(account);
        } 
        [HttpGet]
        [Route("api/account/LoginAccount/{email}/{password}")]
        public IHttpActionResult LoginAccount(string email, string password)
        {
            var CheckForExistence = db.Accounts.Where(x => x.Email == email && x.Password == password);
            if (!CheckForExistence.Any())
            {
                return NotFound();
            }

            return Ok(CheckForExistence);
        }

        // PUT: api/Accounts/5
        [HttpPut]
        [Route("api/Account/PutAccount/{id}")]
        public IHttpActionResult PutAccount(int id, Account account)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != account.Id_Account)
            {
                return BadRequest();
            }

            db.Entry(account).State = EntityState.Modified;

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!AccountExists(id))
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

        // POST: api/Accounts
        [HttpPost]
        [Route("api/Account/PostAccount")]
        public IHttpActionResult PostAccount(Account account)
        {
            var CheckForExistence = db.Accounts.Where(x => x.Email == account.Email);
            if (CheckForExistence.Any())
            {
                return Ok("0");
            }
            Account Addaccount = new Account();
            Addaccount.Name = account.Name;
            Addaccount.Email = account.Email;
            Addaccount.Password = account.Password;
            Addaccount.Numberphone = account.Numberphone;
            Addaccount.Address = account.Address;
            Addaccount.JoinDate = DateTime.Now.ToString("MM/dd/yyyy HH:mm:ss");
            Addaccount.Picture = account.Picture;
            db.Accounts.Add(Addaccount);
            db.SaveChanges();
            return Ok("1");
        }

   
        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool AccountExists(int id)
        {
            return db.Accounts.Count(e => e.Id_Account == id) > 0;
        }
    }
}