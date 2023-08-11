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

    [Table("Love")]
    public partial class Love
    {
        [Key]
        public int Id_love { get; set; }

        public int? Id_Account { get; set; }

        public int? Id_product { get; set; }

        public string Notes { get; set; }
    }
}