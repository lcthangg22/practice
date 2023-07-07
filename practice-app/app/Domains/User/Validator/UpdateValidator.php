<?php

namespace App\Domains\User\Validator;

use App\Domains\User\Model\DbUser;

class UpdateValidator
{
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

        // Kiểm tra new password
        if (!isset($params['new_password']) || empty($params['new_password'])) {
            $messages['new_password'] = 'Trường new password không được bỏ trống!';
        } elseif (strlen($params['password']) < 6) {
            $messages['new_password'] = 'Trường new password phải có ít nhất 6 ký tự!';
        } else {
            $checkNewPassword = DbUser::where('password', md5($params['new_password']))->exists();
            if ($checkNewPassword) {
                $messages['new_password'] = 'Mật khẩu mới trùng với mật khẩu cũ!';
            }
        }


        // Kiểm tra email
        if (!isset($params['email']) || empty($params['email'])) {
            $messages['email'] = 'Trường email không được bỏ trống!';
        } elseif (!filter_var($params['email'], FILTER_VALIDATE_EMAIL)) {
            $messages['email'] = 'Trường email không hợp lệ!';
        } else {
            $checkEmail = DbUser::where('email', $params['email'])->exists();
            if (!$checkEmail) {
                $messages['email'] = 'Email không tồn tại!';
            }
        }

        return $messages;
    }
}