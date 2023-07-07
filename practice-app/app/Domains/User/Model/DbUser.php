<?php

namespace App\Domains\User\Model;

use DateTimeInterface;
use Illuminate\Database\Eloquent\Model;

class DbUser extends Model
{
    protected $table = 'users';

    protected $fillable = [
        'username',
        'password',
        'fullname',
        'email',
        'jwt_token'
    ];

    public const CREATED_AT = 'created';

    public const UPDATED_AT = 'modified';

    protected function serializeDate(DateTimeInterface $date): string
    {
        return $date->format('Y-m-d H:i:s');
    }
}