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

    public partial class ProductDetail
    {
        [Key]
        public int Id_productdetails { get; set; }

        public int? Id_product { get; set; }

        public string Size { get; set; }

        public int? Price { get; set; }

        public int? Promotionalprice { get; set; }
    }
}