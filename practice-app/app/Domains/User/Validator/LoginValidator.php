<?php

namespace App\Domains\User\Validator;

use App\Domains\User\Model\DbUser;
use Illuminate\Support\Facades\Validator;

class LoginValidator
{
//    public function validation(array $params): \Illuminate\Validation\Validator
//    {
//        $validator =  Validator::make($params, [
//            'username' => 'required',
//            'password' => 'required|min:6',
//            'email' => 'required|regex:/^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9._%+-]+\.[a-zA-Z0-9._%+-]+$/i',
//        ]);
//
//        $validator->after(function ($validator) use ($params) {
//            $usernameExists = DbUser::where('username', $params['username'])->exists();
//            if (!$usernameExists) {
//                $validator->errors()->add('username', 'Username khong ton tai!');
//            }
//
//            $emailExists = DbUser::where('email', $params['email'])->exists();
//            if (!$emailExists) {
//                $validator->errors()->add('email', 'Email khong ton tai!');
//            }
//
//            $passWordExists = DbUser::where('password', md5($params['password']))->exists();
//            if (!$emailExists) {
//                $validator->errors()->add('password', 'Mat khau khong dung!');
//            }
//        });
//
//        $validator->setCustomMessages([
//            'required' => 'Truong :attribute khong duoc bo trong!',
//            'password.min' => 'Truong :attribute phai lon hon 6 ki tu!',
//            'email.regex' => 'Truong :attribute khong hop le!',
//        ]);
//
//        return $validator;
//    }

    public function validation(array $params): array
    {
        $messages = [];

        // Kiểm tra username
        if (!isset($params['username']) || empty($params['username'])) {
            $messages['username'] = 'Trường username không được bỏ trống!';
        } else {
            $checkUsername = DbUser::where('username', $params['username'])->exists();
            if (!$checkUsername) {
                $messages['username'] = 'Username không tồn tại!';
            }
        }

        // Kiểm tra password
        if (!isset($params['password']) || empty($params['password'])) {
            $messages['password'] = 'Trường password không được bỏ trống!';
        } elseif (strlen($params['password']) < 6) {
            $messages['password'] = 'Trường password phải có ít nhất 6 ký tự!';
        } else {
            $checkPassword = DbUser::where('password', md5($params['password']))->exists();
            if (!$checkPassword) {
                $messages['password'] = 'Mật khẩu không đúng!';
            }
        }

        return $messages;
    }
}