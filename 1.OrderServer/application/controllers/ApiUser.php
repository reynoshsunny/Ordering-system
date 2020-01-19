<?php

class ApiUser extends CI_controller
{
	public function __construct()
	{
		parent::__construct();
		$this->load->database();
		$this->load->model('Order_api_Model');
		$this->load->helper('text');
	}

	public function index()
	{
		$this->load->view('welcome_message');
	}


	public function SignUp()
	{
		$name = $_POST['NAME'];
		$address = $_POST['ADDRESS'];
		$place = $_POST['PLACE'];
		$phone = $_POST['PHONE_NO'];
		$pin = $_POST['PINCODE'];
		$email = $_POST['EMAIL_ID'];
		$password = $_POST['PASSWORD'];
		$xcord = $_POST['X_CORD'];
		$ycord = $_POST['Y_CORD'];
		$referal ='HD7U2DOI';
		$joindt ='2019-05-23';
		$active = '1';

		$query = $this->db->query("SELECT * FROM M_CUSTOMERS WHERE EMAIL_ID = '$email' or PHONE_NO = $phone ");

		if($query->num_rows() >= 1)
		{
			$response['error'] = true;
			$response['message'] = 'Already registerd user';
			echo json_encode($response);
		}
		else
		{
			$query = $this->db->query("INSERT INTO M_CUSTOMERS (CUS_ID, NAME, ADDRESS, PLACE,PINCODE, PHONE_NO, EMAIL_ID, PASSWORD, X_CORD, Y_CORD, REFERALCD, JOINDT, ACTIVE) VALUES (0, '$name','$address','$place','$pin',$phone,'$email','$password','$xcord','$ycord','$referal','$joindt',$active ) ");

			$response['error'] = false;
			$response['message'] = 'User added';
			echo json_encode($response);
		}

	}


	public function Login()
	{
		$password = $_POST['PASSWORD'];
		$phone = $_POST['PHONE_NO'];

		$query = $this->db->query("SELECT * FROM M_CUSTOMERS WHERE PASSWORD = '$password' AND PHONE_NO = $phone ");

		if($query->num_rows() == 1)
		{
			$id= $query->row()->CUS_ID;
			$result = $this->db->query("SELECT CUS_ID, NAME, PHONE_NO, ADDRESS, PINCODE, REFERALCD FROM M_CUSTOMERS WHERE CUS_ID = $id ");

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

	public function login_api()
	{

		$email = $_POST['EMAIL_ID'];
		$phone = $_POST['PHONE_NO'];

		$query = $this->db->query("SELECT * FROM M_CUSTOMERS WHERE EMAIL_ID = '$email' AND PHONE_NO = $phone ");

		if($query->num_rows() >= 1)
		{
			$response['error'] = false;
			$response['message'] = 'User Found';
			echo json_encode($response);
		}

		else
		{
			$response['error'] = true;
			$response['message'] = 'User not Found ';
			echo json_encode($response);
		}

	}


	public function UpdateAddress()
	{
		$id=$_POST['CUS_ID'];
		$address=$_POST['ADDRESS'];
		$place=$_POST['PLACE'];
		$pincode = $_POST['PINCODE'];
		$query = $this->db->query("SELECT ADDRESS FROM M_CUSTOMERS WHERE CUS_ID = $id ");

		if($query->num_rows() == 1)
		{
			$query = $this->db->query("UPDATE M_CUSTOMERS SET ADDRESS='$address', PLACE = '$place' ,PINCODE = '$pincode'  WHERE CUS_ID =$id");
			$response['error'] = false;
			$response['message'] = 'Address updated sucessfully';
			echo json_encode($response);
		}

		else
		{
			$response['error'] = true;
			$response['message'] = 'no id found';
			echo json_encode($response);
		}

	}

	public function UpdatePlace()
	{
		$id=$_GET['CUS_ID'];
		$place=$_POST['PLACE'];
		$query = $this->db->query("SELECT PLACE FROM M_CUSTOMERS WHERE CUS_ID=$id ");

		if($query->num_rows() == 1)
		{
			$query = $this->db->query("UPDATE M_CUSTOMERS SET PLACE='$place' WHERE CUS_ID =$id");
			$response['error'] = false;
			$response['message'] = 'Place updated sucessfully';
			echo json_encode($response);
		}

		else
		{
			$response['error'] = true;
			$response['message'] = 'no id found';
			echo json_encode($response);
		}

	}

	public function UpdatePhone()
	{
		$id=$_GET['CUS_ID'];
		$phone=$_POST['PHONE_NO'];
		$query = $this->db->query("SELECT * FROM M_CUSTOMERS WHERE CUS_ID=$id ");

		if($query->num_rows() == 1)
		{
			$q=$this->db->query("SELECT * FROM M_CUSTOMERS WHERE PHONE_NO=$phone");

			if($q->num_rows()==1)
			{
				$response['error'] = true;
				$response['message'] = 'This phone number is already registerd';
				echo json_encode($response);
			}
			else
			{
				$query = $this->db->query("UPDATE M_CUSTOMERS SET PHONE_NO='$phone' WHERE CUS_ID =$id");
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

	public function UpdatePassword()
	{
		$id=$_POST['CUS_ID'];
		$password=$_POST['PASSWORD'];
		$query = $this->db->query("SELECT PASSWORD FROM M_CUSTOMERS WHERE CUS_ID=$id ");

		if($query->num_rows() == 1)
		{
			$query = $this->db->query("UPDATE M_CUSTOMERS SET PASSWORD='$password' WHERE CUS_ID =$id");
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



	public function IsSigned()
	{

		$phone = $_POST['PHONE_NO'];
		$query = $this->db->query("SELECT PHONE_NO FROM M_CUSTOMERS WHERE PHONE_NO = $phone ");

		if($query->num_rows() >= 1)
		{
			$response['error'] = false;
			$response['message'] = 'Phone number already exist';
			echo json_encode($response);
		}
		else
		{
			$response['error'] = true;
			$response['message'] = 'No data found ';
			echo json_encode($response);
		}

	}




	public function AddOrder()
	{
		$Id=$_POST['CUS_ID'];			
		$ImageData = $_POST['IMAGE_DATA'];
		$Remarks = $_POST['REMARKS'];
	
		$query = $this->db->query("SELECT * FROM M_CUSTOMERS WHERE CUS_ID = '$Id' ");

		if($query->num_rows() >= 1)
		{
			$query = $this->db->query("INSERT INTO ORDERS (ID_ORDER, ID_CUSTOMER, STATUS, IMAGEDATA,REMARK) VALUES (0, '$Id','O','$ImageData','$Remarks' ) ");
			$response['error'] = false;
			$response['message'] = 'Order received';
			echo json_encode($response);
		}
		else
		{
			
			$response['error'] = true;
			$response['message'] = 'User not found';
			echo json_encode($response);
		}

	}



}


?>
