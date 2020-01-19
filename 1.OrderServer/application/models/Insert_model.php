<?php
	class Insert_model extends CI_Model

	{
	function insertrecords($name,$address,$place,$phone,$email,$password,$xcord,$ycord,$referal,$joindt)
		{
			$in_array=['NAME'=>$name,'ADDRESS'=>$address,'PLACE'=>$place,'PHONE_NO'=>$phoneno,'EMAIL_ID'=>$email,'X_CORD'=>$xcord,'Y_CORD'=>$ycord,'REFERALCD'=>$referal,'JOINDT'=>$joindt];
			$this->db->insert('m_customers',$in_array);
			$id=$this->db->insert_id();
			 return $id;
		}
	}