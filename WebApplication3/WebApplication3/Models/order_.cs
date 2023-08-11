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

    public partial class order_
    {
        [Key]
        public int Id_Order { get; set; }

        public int? Id_Account { get; set; }
        public int? Id_productdetails { get; set; }
        public int? Quantity { get; set; }
        public int? TotalMoney { get; set; }

        public string Message { get; set; }

        public string PaymentMethod { get; set; }

        public string DateTime { get; set; }

        public int? Status { get; set; }

        public string Notes { get; set; }
    }
}