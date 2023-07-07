<?php

namespace App\Domains\User\Repository;

use App\Domains\User\Model\DbUser;
use App\Repository\Repository;

class UserRepository extends Repository
{
    public function getModel(): string
    {
        return DbUser::class;
    }
}