using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace WebApplication3.Models
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    [Table("Product")]
    public partial class Product
    {
        [Key]
        public int Id_product { get; set; }

        public int? Id_danhmuc { get; set; }

        public string NameProduct { get; set; }

        public string Content { get; set; }

        public int? sales { get; set; }

        public int? views { get; set; }

        public string Imagelinks { get; set; }

        public string JoinDate { get; set; }
    }
}