<?php

class ApiAdmins extends CI_controller
{
	public function __construct()
	{
		parent::__construct();
		$this->load->database();

		$this->load->helper('text');
	}

	public function index()
	{
		$this->load->view('welcome_message');
	}

	public function Admin_check_password()
	{
		$admin_id=$_POST['ADMIN_ID'];
		$admin_password=$_POST['ADMIN_PASSWORD'];
		$query = $this->db->query("SELECT * FROM ADMIN WHERE ADMIN_ID=$admin_id AND ADMIN_PASSWORD = '$admin_password'");
		if($query->num_rows() == 1)
		{
			$response['error'] = false;
			$response['message'] = 'User found ';
			echo json_encode($response);
		}
		else
		{
			$response['error'] = true;
			$response['message'] = 'Incorrect password ';
			echo json_encode($response);
		}
	}

	public function IsAdminSignUp()
	{
		$admin_phone=$_POST['ADMIN_PHONE'];
		$query = $this->db->query("SELECT * FROM ADMIN WHERE  ADMIN_PHONE = $admin_phone ");
		if($query->num_rows() == 1)
		{
			$response['error'] = false;
			$response['message'] = 'User found ';
			echo json_encode($response);
		}
		else
		{
			$response['error'] = true;
			$response['message'] = 'User not found ';
			echo json_encode($response);
		}
	}




	public function AdminLogin()
	{
		$admin_password=$_POST['ADMIN_PASSWORD'];
		$admin_phone = $_POST['ADMIN_PHONE'];

		$query = $this->db->query("SELECT * FROM ADMIN WHERE ADMIN_PASSWORD = '$admin_password' AND ADMIN_PHONE = $admin_phone ");

		if($query->num_rows() == 1)
		{
			$admin_id= $query->row()->ADMIN_ID;
			$result = $this->db->query("SELECT ADMIN_ID, ADMIN_NAME, ADMIN_PHONE, ADMIN_ADDRESS FROM ADMIN");

			if($result->num_rows() >= 1)
			{
				$response['error'] = false;
				$response['message'] = 'User found';
				$response['user'] = $result->result();
				echo json_encode($response);

			}

		}

		else
		{
			$response['error'] = true;
			$response['message'] = 'User not found ';
			echo json_encode($response);
		}

	}

	public function AdminUpdateAddress()
	{
		$admin_id=$_POST['ADMIN_ID'];
		$admin_address=$_POST['ADMIN_ADDRESS'];
		$query = $this->db->query("SELECT ADMIN_ADDRESS FROM ADMIN WHERE ADMIN_ID= $admin_id ");

		if($query->num_rows() == 1)
		{

			$query = $this->db->query("UPDATE ADMIN SET ADMIN_ADDRESS='$admin_address' WHERE ADMIN_ID = $admin_id ");

			$response['error'] = false;
			$response['message'] = 'Address updated sucessfully';
			echo json_encode($response);

		}

		else
		{
			$response['error'] = true;
			$response['message'] = 'No id found';
			echo json_encode($response);
		}

	}

	public function AdminUpdatePhone()
	{
		$admin_id=$_POST['ADMIN_ID'];
		$admin_phone = $_POST['ADMIN_PHONE'];
		$query = $this->db->query("SELECT * FROM ADMIN WHERE ADMIN_ID=$admin_id ");

		if($query->num_rows() == 1)
		{
			$q=$this->db->query("SELECT * FROM ADMIN WHERE ADMIN_PHONE=$admin_phone");

			if($q->num_rows()==1)
			{
				$response['error'] = true;
				$response['message'] = 'This phone number is already registerd';
				echo json_encode($response);
			}
			else
			{
				$query = $this->db->query("UPDATE ADMIN SET ADMIN_PHONE='$admin_phone' WHERE ADMIN_ID =$admin_id");
				$response['error'] = false;
				$response['message'] = 'Your mobile number updated sucessfully';
				echo json_encode($response);
			}
		}

		else
		{
			$response['error'] = true;
			$response['message'] = 'no id found';
			echo json_encode($response);
		}

	}

	public function AdminUpdatePassWord()
	{
		$admin_id=$_POST['ADMIN_ID'];
		$admin_password=$_POST['ADMIN_PASSWORD'];
		$query = $this->db->query("SELECT ADMIN_PASSWORD FROM ADMIN WHERE ADMIN_ID=$admin_id ");

		if($query->num_rows() == 1)
		{
			$query = $this->db->query("UPDATE ADMIN SET ADMIN_PASSWORD='$admin_password' WHERE ADMIN_ID =$admin_id");
			$response['error'] = false;
			$response['message'] = 'Password updated sucessfully';
			echo json_encode($response);
		}

		else
		{
			$response['error'] = true;
			$response['message'] = 'no id found';
			echo json_encode($response);
		}

	}

	Public function GetCustomersList()
	{

		$query = $this->db->query("SELECT * FROM M_CUSTOMERS");

		if($query->num_rows() >= 1)
		{

			$result = $this->db->query("SELECT NAME, ADDRESS, PLACE, PINCODE,PHONE_NO, EMAIL_ID, X_CORD,Y_CORD, JOINDT FROM M_CUSTOMERS order by NAME");

			$response['user'] = $result->result();
			echo json_encode($result->result());

		}
		else
		{
			$response['error'] = true;
			$response['message'] = 'No customers in the table';
			echo json_encode($response);
		}
	}
	
		Public function GetPinCodeList()
	{

		$query = $this->db->query("SELECT * FROM M_PINCODES ");

		if($query->num_rows() >= 1)
		{

			$result = $this->db->query("SELECT PINCODE, PLACE FROM M_PINCODES order by PLACE");

			$response['user'] = $result->result();
			echo json_encode($result->result());

		}
		else
		{
			$response['error'] = true;
			$response['message'] = 'No customers in the table';
			echo json_encode($response);
		}
	}
	

}


?>
