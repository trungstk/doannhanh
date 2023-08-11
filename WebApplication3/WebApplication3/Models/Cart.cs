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

    [Table("Cart")]
    public partial class Cart
    {
        [Key]
        public int Id_Cart { get; set; }

        public int? Id_Account { get; set; }

        public int? Id_productdetails { get; set; }

        public int? Quantity { get; set; }

        public int? TotalMoney { get; set; }
        public string Notes { get; set; }
    }
}