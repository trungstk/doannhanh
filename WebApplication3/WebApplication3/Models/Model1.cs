using System;
using System.ComponentModel.DataAnnotations.Schema;
using System.Data.Entity;
using System.Linq;

namespace WebApplication3.Models
{
    public partial class Model1 : DbContext
    {
        public Model1()
            : base("name=Model11")
        {
        }


        public virtual DbSet<Account> Accounts { get; set; }
        public virtual DbSet<Cart> Carts { get; set; }
        public virtual DbSet<Category> Categorys { get; set; }
        public virtual DbSet<Love> Loves { get; set; }
        public virtual DbSet<Notify> Notifies { get; set; }
        public virtual DbSet<order_> order_ { get; set; }
        public virtual DbSet<Product> Products { get; set; }
        public virtual DbSet<ProductDetail> ProductDetails { get; set; }
        protected override void OnModelCreating(DbModelBuilder modelBuilder)
        {
        }
    }
}
