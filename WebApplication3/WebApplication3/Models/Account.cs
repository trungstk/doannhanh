namespace WebApplication3.Models
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    [Table("Account")]
    public partial class Account
    {
        [Key]
        public int Id_Account { get; set; }

        public string Name { get; set; }

        public string Email { get; set; }

        public string Password { get; set; }

        public string Numberphone { get; set; }

        public string Address { get; set; }

        public string JoinDate { get; set; }

        public string Picture { get; set; }
    }
}
