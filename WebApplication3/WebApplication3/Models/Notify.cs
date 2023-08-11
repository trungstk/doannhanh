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

    [Table("Notify")]
    public partial class Notify
    {
        [Key]
        public int Id_notify { get; set; }

        public int? Id_Account { get; set; }
        public int? Id_productdetails { get; set; }
        public int? Status { get; set; }
        public int? watched { get; set; }

    }
}