<?php
	class Order_api_Model extends CI_model
	{
		public function login_api($email,$phone)
		{
			$query=$this->db->query("select * from m_customers where EMAIL_ID ='$email' and PHONE_NO='$phone'");
			
				return $query->result();
			
		}
	}


?>