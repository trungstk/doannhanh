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

    [Table("Category")]
    public partial class Category
    {
        [Key]
        public int Id_Category { get; set; }

        public string NameCategory { get; set; }

        public string PictureCategory { get; set; }

        public string JoinDate { get; set; }

        public string Note { get; set; }

    }
}